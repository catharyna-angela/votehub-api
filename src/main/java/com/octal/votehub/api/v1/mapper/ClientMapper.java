package com.octal.votehub.api.v1.mapper;

import com.octal.votehub.api.v1.dto.client.CreateClientDTO;
import com.octal.votehub.api.v1.dto.client.ResponseClientDTO;
import com.octal.votehub.api.v1.dto.client.UpdateClientDTO;
import com.octal.votehub.api.v1.domain.entity.Client;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ClientMapper {

    private ClientMapper() {
    }

    public static Client toClient(CreateClientDTO createClientDTO) {
        return new ModelMapper().map(createClientDTO, Client.class);
    }

    public static Client toClient(UpdateClientDTO updateClientDTO){
        return new ModelMapper().map(updateClientDTO, Client.class);
    }

    public static ResponseClientDTO toResponseClientDTO(Client client) {
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
