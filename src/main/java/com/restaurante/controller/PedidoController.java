package com.restaurante.controller;

import com.restaurante.dto.AddClienteDto;
import com.restaurante.dto.AddPedidoDto;
import com.restaurante.error.ResourceNotFoundExeption;
import com.restaurante.model.Cliente;
import com.restaurante.model.Pedido;
import com.restaurante.service.ClienteService;
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
@RequestMapping("/pedido")
public class PedidoController {


    @Autowired
    PedidoService pedidoService;

    @Autowired
    ClienteService clienteService;

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
    public Pedido salvar(@Valid @RequestBody AddPedidoDto addPedidoDto){

        Optional<Cliente> cliente = clienteService.getById(addPedidoDto.getClienteId());
        if(cliente.isPresent()){
            Pedido pedido = new Pedido(null,addPedidoDto.getDataPedido(), addPedidoDto.getItem(),cliente.get(),null);
            return pedidoService.save(pedido);
        }
       else{
           throw new ResourceNotFoundExeption("Cliente não encontrado");
        }

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pedido> listaPedido(){
        return pedidoService.getAll();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pedido buscarPedidoPorId(@PathVariable("id") Long id){
        return pedidoService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPedido(@PathVariable("id") Long id){
        pedidoService.getById(id)
                .map(pedido -> {
                    pedidoService.deleteById(pedido.getPedidoId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPedido(@PathVariable("id") Long id,@Valid @RequestBody AddPedidoDto addPedidoDto){

        Optional<Cliente> cliente = clienteService.getById(addPedidoDto.getClienteId());
        if(cliente.isPresent()){
            pedidoService.getById(id)
                    .map(pedidoBanco -> {
                        pedidoBanco.setDataPedido(addPedidoDto.getDataPedido());
                        pedidoBanco.setItem(addPedidoDto.getItem());
                        pedidoBanco.setCliente(cliente.get());
                        pedidoService.save(pedidoBanco);
                        return Void.TYPE;
                    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
        }
        else{
            throw new ResourceNotFoundExeption("Cliente não encontrado");
        }

    }
}
