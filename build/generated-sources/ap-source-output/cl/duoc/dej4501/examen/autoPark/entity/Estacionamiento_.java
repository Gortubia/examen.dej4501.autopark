package cl.duoc.dej4501.examen.autoPark.entity;

import cl.duoc.dej4501.examen.autoPark.entity.Ticket;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T20:46:57")
@StaticMetamodel(Estacionamiento.class)
public class Estacionamiento_ { 

    public static volatile ListAttribute<Estacionamiento, Ticket> ticketList;
    public static volatile SingularAttribute<Estacionamiento, String> nombreEstacionamiento;
    public static volatile SingularAttribute<Estacionamiento, String> direccion;
    public static volatile SingularAttribute<Estacionamiento, Integer> idEstacionamiento;

}