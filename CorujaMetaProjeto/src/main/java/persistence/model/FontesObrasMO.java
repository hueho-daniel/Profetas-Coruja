package persistence.model;
import java.util.List;

import datatype.SimpleDate;

public class FontesObrasMO {

	private int id;

	private String titulo;

	private String comentarios;

	private String refverenciasirCulacaoObra;

	private String URL;

	private String copiasManuscritas;

	private String traducoes;

	private SimpleDate dataImpressao;

	private String editor;

	private GrupoMovimentoMO grupoMovimento;

	private LocalMO localImpressao;

	private ClassificacaoMO classificao;

	private List<PalavraChaveMO> palavraChave;

	private List<FontesObrasMO> obrasCitadas;

	private List<PersonagemMO> leitores;

	private List<PersonagemMO> personagens;

	private List<PersonagemMO> autoresCitados;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getRefverenciasirCulacaoObra() {
		return refverenciasirCulacaoObra;
	}

	public void setRefverenciasirCulacaoObra(String refverenciasirCulacaoObra) {
		this.refverenciasirCulacaoObra = refverenciasirCulacaoObra;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getCopiasManuscritas() {
		return copiasManuscritas;
	}

	public void setCopiasManuscritas(String copiasManuscritas) {
		this.copiasManuscritas = copiasManuscritas;
	}

	public String getTraducoes() {
		return traducoes;
	}

	public void setTraducoes(String traducoes) {
		this.traducoes = traducoes;
	}

	public SimpleDate getDataImpressao() {
		return dataImpressao;
	}

	public void setDataImpressao(SimpleDate dataImpressao) {
		this.dataImpressao = dataImpressao;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public GrupoMovimentoMO getGrupoMovimento() {
		return grupoMovimento;
	}

	public void setGrupoMovimento(GrupoMovimentoMO grupoMovimento) {
		this.grupoMovimento = grupoMovimento;
	}

	public LocalMO getLocalImpressao() {
		return localImpressao;
	}

	public void setLocalImpressao(LocalMO localImpressao) {
		this.localImpressao = localImpressao;
	}

	public ClassificacaoMO getClassificao() {
		return classificao;
	}

	public void setClassificao(ClassificacaoMO classificao) {
		this.classificao = classificao;
	}

	public List<PalavraChaveMO> getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(List<PalavraChaveMO> palavraChave) {
		this.palavraChave = palavraChave;
	}

	public List<FontesObrasMO> getObrasCitadas() {
		return obrasCitadas;
	}

	public void setObrasCitadas(List<FontesObrasMO> obrasCitadas) {
		this.obrasCitadas = obrasCitadas;
	}

	public List<PersonagemMO> getLeitores() {
		return leitores;
	}

	public void setLeitores(List<PersonagemMO> leitores) {
		this.leitores = leitores;
	}

	public List<PersonagemMO> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<PersonagemMO> personagens) {
		this.personagens = personagens;
	}

	public List<PersonagemMO> getAutoresCitados() {
		return autoresCitados;
	}

	public void setAutoresCitados(List<PersonagemMO> autoresCitados) {
		this.autoresCitados = autoresCitados;
	}

}
