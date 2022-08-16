package com.restaurante.controller;

import com.restaurante.dto.AddEntregaDto;
import com.restaurante.error.ResourceNotFoundExeption;
import com.restaurante.model.Entrega;
import com.restaurante.model.Pedido;
import com.restaurante.service.EntregaService;
import com.restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

    @Autowired
    EntregaService entregaService;
    @Autowired
    PedidoService pedidoService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExeption(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega salvar(@Valid @RequestBody AddEntregaDto addEntregaDto){

        Optional<Pedido> pedido = pedidoService.getById(addEntregaDto.getPedidoId());
        if(pedido.isPresent() && addEntregaDto.getDataEntrega().compareTo(pedido.get().getDataPedido()) >= 0 ){
            Entrega entrega = new Entrega(null,addEntregaDto.getDataEntrega(), pedido.get());
            return entregaService.save(entrega);
        }
        else if(addEntregaDto.getDataEntrega().compareTo(pedido.get().getDataPedido()) < 0 ){
            throw new ResourceNotFoundExeption("Data de entrega não pode ser menor que data do pedido");
        }
        else{
            throw new ResourceNotFoundExeption("Pedido não encontrado");
        }

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entrega> listaEntrega(){
        return entregaService.getAll();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entrega buscarEntregaPorId(@PathVariable("id") Long id){
        return entregaService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada"));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerEntrega(@PathVariable("id") Long id){
        entregaService.getById(id)
                .map(entrega -> {
                    entregaService.deleteById(entrega.getEntregaId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarEntrega(@PathVariable("id") Long id,@Valid @RequestBody AddEntregaDto addEntregaDto){

        Optional<Pedido> pedido = pedidoService.getById(addEntregaDto.getPedidoId());
        if(pedido.isPresent()){
            entregaService.getById(id)
                    .map(entregaBanco -> {
                        entregaBanco.setDataEntrega(addEntregaDto.getDataEntrega());
                        entregaBanco.setPedido(pedido.get());
                        entregaService.save(entregaBanco);
                        return Void.TYPE;
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada"));
        }
        else{
            throw new ResourceNotFoundExeption("Pedido não encontrado");
        }

    }
}
