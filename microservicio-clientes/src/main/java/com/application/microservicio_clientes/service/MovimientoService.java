import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getNumeroCuenta()).orElseThrow();
        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }
        cuenta.setSaldoInicial(nuevoSaldo);
        movimiento.setSaldo(nuevoSaldo);
        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }
}