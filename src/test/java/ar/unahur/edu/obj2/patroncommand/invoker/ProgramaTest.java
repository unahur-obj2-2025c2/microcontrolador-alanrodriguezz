package ar.unahur.edu.obj2.patroncommand.invoker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Microcontrolador;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.Add;
import ar.unahur.edu.obj2.patroncommand.operaciones.Lod;
import ar.unahur.edu.obj2.patroncommand.operaciones.Lodv;
import ar.unahur.edu.obj2.patroncommand.operaciones.Nop;
import ar.unahur.edu.obj2.patroncommand.operaciones.Str;
import ar.unahur.edu.obj2.patroncommand.operaciones.Swap;

public class ProgramaTest {

    private Programa p = new Programa(null);
    private Programable micro = new Microcontrolador();

    @BeforeEach()
    void setUp(){
        p.vaciarLista();
        p.resetearMicro(micro);
    }

    @Test
    void avanzar3posicionesElProgramCounter(){
        p.agregarOperacion(new Nop());
        p.agregarOperacion(new Nop());
        p.agregarOperacion(new Nop());

        p.ejecutar(micro);

        assertEquals(3, micro.getProgramCounter());
    }

    @Test
    void sumar20mas17YObtener37EnAcumuladorA(){
        p.agregarOperacion(new Lodv(20));
        p.agregarOperacion(new Swap());
        p.agregarOperacion(new Lodv(17));
        p.agregarOperacion(new Add());

        p.ejecutar(micro);

        assertEquals(37, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
        assertEquals(4, micro.getProgramCounter());
    }

    @Test
    void sumar2mas8mas5EnAcumuladorA(){
        p.agregarOperacion(new Lodv(2));
        p.agregarOperacion(new Str(0));
        p.agregarOperacion(new Lodv(8));

        p.agregarOperacion(new Swap());
        p.agregarOperacion(new Lodv(5));
        p.agregarOperacion(new Add());

        p.agregarOperacion(new Swap());
        p.agregarOperacion(new Lod(0));
        p.agregarOperacion(new Add());

        p.ejecutar(micro);

        assertEquals(15, micro.getAcumuladorA());
    }
}
