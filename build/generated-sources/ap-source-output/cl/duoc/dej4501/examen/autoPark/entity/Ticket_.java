package cl.duoc.dej4501.examen.autoPark.entity;

import cl.duoc.dej4501.examen.autoPark.entity.Estacionamiento;
import cl.duoc.dej4501.examen.autoPark.entity.Voucher;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T20:46:57")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Integer> idTicket;
    public static volatile SingularAttribute<Ticket, Long> monto;
    public static volatile SingularAttribute<Ticket, Estacionamiento> idEstacionamiento;
    public static volatile SingularAttribute<Ticket, Voucher> idVoucher;

}