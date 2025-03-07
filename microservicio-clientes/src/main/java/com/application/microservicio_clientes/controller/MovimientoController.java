import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public Movimiento registrarMovimiento(@RequestBody Movimiento movimiento) {
        return movimientoService.registrarMovimiento(movimiento);
    }
}