package com.octalsystems.votehub.v1.dto.mapper;

import com.octalsystems.votehub.v1.dto.CreateClientDTO;
import com.octalsystems.votehub.v1.dto.ResponseClientDTO;
import com.octalsystems.votehub.v1.entity.Client;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ClientMapper {

    public static Client toClient(CreateClientDTO createClientDTO) { //converte DTO para Client (toClient)
        return new ModelMapper().map(createClientDTO, Client.class);
    }

    public static ResponseClientDTO toCreateResponseClientDTO(Client client) { //DTO de resposta para quando criar um cliente.
        String roleName = client.getRole().name().substring("ROLE_".length());
         PropertyMap<Client, ResponseClientDTO> props = new PropertyMap<Client, ResponseClientDTO>() {
            @Override
            protected void configure() {
                map().setRole(roleName);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);

        return mapper.map(client, ResponseClientDTO.class);
    }

}
