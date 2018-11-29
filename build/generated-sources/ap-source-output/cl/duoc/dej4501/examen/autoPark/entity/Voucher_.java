package cl.duoc.dej4501.examen.autoPark.entity;

import cl.duoc.dej4501.examen.autoPark.entity.Mediospago;
import cl.duoc.dej4501.examen.autoPark.entity.OpcionEnvioBoleta;
import cl.duoc.dej4501.examen.autoPark.entity.Ticket;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-29T20:43:26")
@StaticMetamodel(Voucher.class)
public class Voucher_ { 

    public static volatile SingularAttribute<Voucher, String> emailCliente;
    public static volatile SingularAttribute<Voucher, Integer> total;
    public static volatile ListAttribute<Voucher, Ticket> ticketList;
    public static volatile SingularAttribute<Voucher, String> nombreCliente;
    public static volatile SingularAttribute<Voucher, Mediospago> idmediosPagos;
    public static volatile SingularAttribute<Voucher, Integer> telefonoCliente;
    public static volatile SingularAttribute<Voucher, Integer> idVoucher;
    public static volatile SingularAttribute<Voucher, Integer> rutCliente;
    public static volatile SingularAttribute<Voucher, OpcionEnvioBoleta> idopEnvioBoleta;

}