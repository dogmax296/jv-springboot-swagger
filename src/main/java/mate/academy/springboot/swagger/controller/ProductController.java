package mate.academy.springboot.swagger.controller;

import mate.academy.springboot.swagger.dto.ProductRequestDto;
import mate.academy.springboot.swagger.dto.ProductResponseDto;
import mate.academy.springboot.swagger.mapper.ProductMapper;
import mate.academy.springboot.swagger.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    @Autowired
    public ProductController(ProductService service,
                             ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        return mapper.toDto(service.create(mapper.toModel(dto)));
    }

    @GetMapping("/get")
    public ProductResponseDto get(@RequestParam Long id) {
        return mapper.toDto(service.get(id));
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        service.delete(id);
    }

    @PutMapping("/update")
    public ProductResponseDto update(@RequestBody ProductRequestDto dto) {
        return mapper.toDto(service.update(mapper.toModel(dto)));
    }

    @GetMapping
    private List<ProductResponseDto> getAll(@RequestParam Map<String, String> params) {
        return service.getAll(params)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
