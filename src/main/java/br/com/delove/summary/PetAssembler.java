package br.com.delove.summary;

import br.com.delove.model.Pet;
import br.com.delove.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PetAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PetSummaryModel toModel(Pet pet){
        return modelMapper.map(pet, PetSummaryModel.class);
    }

    public List<PetSummaryModel> toCollectionModel(List<Pet> pets){
        return pets.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
