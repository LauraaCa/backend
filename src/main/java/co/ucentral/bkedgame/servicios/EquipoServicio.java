package co.ucentral.bkedgame.servicios;

import co.ucentral.bkedgame.persistencia.entidades.Equipo;
import co.ucentral.bkedgame.persistencia.repositorios.EquipoRepositorio;
import dto.EquipoDto;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EquipoServicio {

    EquipoRepositorio equipoRepositorio;

    public List<Equipo> obtenerTodos()
    {
        return equipoRepositorio.findAll();
    }

    public EquipoDto crear(EquipoDto equipoDto){
        Equipo equipo = Equipo.builder()
                .nombre(equipoDto.nombre())
                .nombreCorto(equipoDto.nombreCorto())
                .fechaCreacion(equipoDto.fechaCreacion())
                .fechaRegistro(LocalDateTime.now())
                .build();

        if (equipoRepositorio.save(equipo).getId() > 0)
            return equipoDto;
        else return null;

    }

    public Equipo obtenerXNombre(String nombre){
        return  equipoRepositorio.findByNombre(nombre);
    }
    public Equipo obtenerXPK(Long pk){
        /*Optional<Equipo> equipo =  equipoRepositorio.findById(pk);
        if (equipo.isPresent())
            return equipo.get();
        return null;*/
        return equipoRepositorio.findById(pk).orElseThrow(null);
    }



}