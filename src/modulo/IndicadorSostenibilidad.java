package modulo;

/**
 * Representa el indicador de sostenibilidad anual de un centro.
 * <p>
 * Agrupa las métricas clave de sostenibilidad de un centro para un año
 * concreto, incluyendo la huella de carbono, el porcentaje de energía renovable
 * utilizada, el índice social y una puntuación global que sintetiza el
 * desempeño sostenible.
 * </p>
 *
 * @author Aaron
 * @version 1.0
 * @since 1.0
 */
public class IndicadorSostenibilidad {

	/** Identificador único del indicador de sostenibilidad. */
	private int idIndicador;

	/** Identificador del centro al que pertenece el indicador. */
	private int idCentro;

	/** Año al que corresponde el indicador de sostenibilidad. */
	private int anio;

	/**
	 * Huella de carbono registrada para el año, expresada en las unidades del
	 * sistema.
	 */
	private double huellaCarbono;

	/**
	 * Porcentaje de energía procedente de fuentes renovables sobre el total
	 * consumido.
	 */
	private double porcentajeRenovable;

	/** Índice social que mide el impacto social del centro en el año indicado. */
	private double indiceSocial;

	/**
	 * Puntuación global de sostenibilidad que integra los distintos indicadores.
	 */
	private double puntuacionGlobal;

	/**
	 * Construye una nueva instancia de {@code IndicadorSostenibilidad} con todos
	 * sus atributos.
	 *
	 * @param idIndicador         identificador único del indicador
	 * @param idCentro            identificador del centro evaluado
	 * @param anio                año al que corresponde el indicador
	 * @param huellaCarbono       huella de carbono del centro en el año indicado
	 * @param porcentajeRenovable porcentaje de energía renovable utilizada
	 * @param indiceSocial        índice de impacto social del centro
	 * @param puntuacionGlobal    puntuación global de sostenibilidad
	 */
	public IndicadorSostenibilidad(int idIndicador, int idCentro, int anio, double huellaCarbono,
			double porcentajeRenovable, double indiceSocial, double puntuacionGlobal) {
		this.idIndicador = idIndicador;
		this.idCentro = idCentro;
		this.anio = anio;
		this.huellaCarbono = huellaCarbono;
		this.porcentajeRenovable = porcentajeRenovable;
		this.indiceSocial = indiceSocial;
		this.puntuacionGlobal = puntuacionGlobal;
	}

	/**
	 * Devuelve el identificador único del indicador de sostenibilidad.
	 *
	 * @return identificador del indicador
	 */
	public int getIdIndicador() {
		return idIndicador;
	}

	/**
	 * Devuelve el identificador del centro evaluado.
	 *
	 * @return identificador del centro
	 */
	public int getIdCentro() {
		return idCentro;
	}

	/**
	 * Devuelve el año al que corresponde este indicador de sostenibilidad.
	 *
	 * @return año del indicador
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Devuelve la huella de carbono registrada para el año correspondiente.
	 *
	 * @return huella de carbono del centro
	 */
	public double getHuellaCarbono() {
		return huellaCarbono;
	}

	/**
	 * Devuelve el porcentaje de energía procedente de fuentes renovables.
	 *
	 * @return porcentaje de energía renovable (valor entre 0.0 y 100.0)
	 */
	public double getPorcentajeRenovable() {
		return porcentajeRenovable;
	}

	/**
	 * Devuelve el índice social que mide el impacto social del centro.
	 *
	 * @return índice de impacto social
	 */
	public double getIndiceSocial() {
		return indiceSocial;
	}

	/**
	 * Devuelve la puntuación global de sostenibilidad del centro para el año
	 * indicado.
	 *
	 * @return puntuación global de sostenibilidad
	 */
	public double getPuntuacionGlobal() {
		return puntuacionGlobal;
	}
}