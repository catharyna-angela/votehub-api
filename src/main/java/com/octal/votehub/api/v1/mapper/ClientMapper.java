package com.octal.votehub.api.v1.mapper;

import com.octal.votehub.api.v1.dto.client.CreateClientDTO;
import com.octal.votehub.api.v1.dto.client.ResponseCreateClientDTO;
import com.octal.votehub.api.v1.dto.client.UpdateClientDTO;
import com.octal.votehub.api.v1.domain.entity.Client;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ClientMapper {

    public static Client toClient(CreateClientDTO createClientDTO) { //converte DTO para Client (toClient)
        return new ModelMapper().map(createClientDTO, Client.class);
    }

    public static Client toClient(UpdateClientDTO updateClientDTO){
        return new ModelMapper().map(updateClientDTO, Client.class);
    }

    public static ResponseCreateClientDTO toResponseCreateClientDTO(Client client) { //DTO de resposta para quando criar um cliente.
        String roleName = client.getRole().name().substring("ROLE_".length());
         PropertyMap<Client, ResponseCreateClientDTO> props = new PropertyMap<Client, ResponseCreateClientDTO>() {
            @Override
            protected void configure() {
                map().setRole(roleName);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);

        return mapper.map(client, ResponseCreateClientDTO.class);
    }

}
