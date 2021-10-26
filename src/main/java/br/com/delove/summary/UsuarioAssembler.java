package br.com.delove.summary;

import br.com.delove.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UsuarioSummaryModel toModel(Usuario usuario){
        return modelMapper.map(usuario, UsuarioSummaryModel.class);
    }

    public List<UsuarioSummaryModel> toCollectionModel(List<Usuario> usuarios){
        return usuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
