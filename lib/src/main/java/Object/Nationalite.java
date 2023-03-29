package Object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Classe qui permet d'énumerer les nationalités
public enum Nationalite {

	AF("AF", "Afghanistan"),
	AX("AX", "Îles Åland"),
	AL("AL", "Albanie"),
	DZ("DZ", "Algérie"),
	AS("AS", "Samoa américaines"),
	AD("AD", "Andorre"),
	AO("AO", "Angola"),
	AI("AI", "Anguilla"),
	AQ("AQ", "Antarctique"),
	AG ("AG", "Antigua-et-Barbuda"),
	AR("AR", "Argentine"),
	AM("AM", "Arménie"),
	AW("AW", "Aruba"),
	AU("AU", "Australie"),
	AT("AT", "Autriche"),
	AZ("AZ", "Azerbaïdjan"),
	BS("BS", "Bahamas"),
	BH("BH", "Bahreïn"),
	BD("BD", "Bangladesh"),
	BB("BB", "Barbade"),
	BY("BY", "Biélorussie"),
	BE("BE", "Belgique"),
	BZ("BZ", "Bélize"),
	BJ("BJ", "Bénin"),
	BM("BM", "Bermudes"),
	BT("BT", "Bhoutan"),
	BO("BO", "Bolivie"),
	BQ("BQ", "Bonaire, Saint-Eustache et Saba"),
	BA ("BA", "Bosnie-Herzégovine"),
	BW("BW", "Botswana"),
	BV("BV", "Ile Bouvet"),
	BR("BR", "Brésil"),
	IO("IO", "Territoire Britannique de l'Océan Indien"),
	BN("BN", "Brunei Darussalam"),
	BG("BG", "Bulgarie"),
	BF("BF", "Burkina Faso"),
	BI("BI", "Burundi"),
	CV("CV", "Cap-Vert"),
	KH("KH", "Cambodge"),
	CM("CM", "Cameroun"),
	CA("CA", "Canada"),
	KY("KY", "Îles Caïmans"),
	CF("CF", "République centrafricaine"),
	TD("TD", "Tchad"),
	CL("CL", "Chili"),
	CN("CN", "Chine"),
	CX("CX", "L'île Noël"),
	CC("CC", "Îles Cocos"),
	CO("CO", "Colombie"),
	KM("KM", "Comores"),
	CG("CG", "Congo"),
	CD("CD", "République du Congo"),
	CK("CK", "Îles Cook"),
	CR("CR", "Costa Rica"),
	CI("CI", "Côte d'Ivoire"),
	HR("HR", "Croatie"),
	CU("CU", "Cuba"),
	CW("CW", "Curaçao"),
	CY("CY", "Chypre"),
	CZ("CZ", "République Tchèque"),
	NSP("NSP", "Danemark"),
	DJ("DJ", "Djibouti"),
	DM("DM", "Dominique"),
	DO("DO", "République Dominicaine"),
	CE("CE", "Equateur"),
	EG("EG", "Egypte"),
	SV("SV", "Salvador"),
	GQ("GQ", "Guinée Equatoriale"),
	ER("ER", "Erythrée"),
	EE("EE", "Estonie"),
	ET("ET", "Ethiopie"),
	FK("FK", "Îles Falkland"),
	FO("FO", "Îles Féroé"),
	FJ("FJ", "Fidji"),
	FI("FI", "Finlande"),
	FR("FR", "France"),
	GF("GF", "Guyane Française"),
	PF("PF", "Polynésie Française"),
	TF("TF", "Terres Australes Françaises"),
	GA("GA", "Gabon"),
	GM("GM", "Gambie"),
	GE("GE", "Géorgie"),
	DE("DE", "Allemagne"),
	GH("GH", "Ghana"),
	GI("GI", "Gibraltar"),
	GR("GR", "Grèce"),
	GL("GL", "Groenland"),
	GD("GD", "Grenade"),
	GP("GP", "Guadeloupe"),
	GU("GU", "Guam"),
	GT("GT", "Guatemala"),
	GG("GG", "Guernesey"),
	GN("GN", "Guinée"),
	GW("GW", "Guinée-Bissau"),
	GY("GY", "Guyane"),
	HT("HT", "Haïti"),
	HM("HM", "Îles Heard et McDonald"),
	VA("VA", "Saint-Siège"),
	HN("HN", "Honduras"),
	HK("HK", "Hong Kong"),
	HU("HU", "Hongrie"),
	IS("IS", "Islande"),
	IN("IN", "Inde"),
	ID("ID", "Indonésie"),
	IR("IR", "Iran"),
	QI("QI", "Irak"),
	IE("IE", "Irlande"),
	IM("IM", "Île de Man"),
	IL("IL", "Israël"),
	IT("IT", "Italie"),
	JM("JM", "Jamaïque"),
	JP("JP", "Japon"),
	JE("JE", "Jersey"),
	JO("JO", "Jordanie"),
	KZ("KZ", "Kazakhstan"),
	KE("KE", "Kenya"),
	KI("KI", "Kiribati"),
	KP("KP", "Corée du sud"),
	KR("KR", "Corée du nord"),
	KW("KW", "Koweït"),
	KG("KG", "Kirghizistan"),
	LA("LA", "République démocratique populaire lao"),
	LV("LV", "Lettonie"),
	LB("LB", "Liban"),
	LS("LS", "Lesotho"),
	LR("LR", "Libéria"),
	LY("LY", "Libye"),
	LI("LI", "Liechtenstein"),
	LT("LT", "Lituanie"),
	LU("LU", "Luxembourg"),
	MO("MO", "Macao"),
	MK("MK", "Macédoine"),
	MG("MG", "Madagascar"),
	MW("MW", "Malawi"),
	MY("MY", "Malaisie"),
	MV("MV", "Maldives"),
	ML("ML", "Mali"),
	MT("MT", "Malte"),
	MH("MH", "Îles Marshall"),
	MQ("MQ", "Martinique"),
	MR("MR", "Mauritanie"),
	MU("MU", "Maurice"),
	YT("YT", "Mayotte"),
	MX("MX", "Mexique"),
	FM("FM", "Micronésie"),
	MD("MD", "Moldavie"),
	MC("MC", "Monaco"),
	MN("MN", "Mongolie"),
	ME("ME", "Monténégro"),
	MS("MS", "Montserrat"),
	MA("MA", "Maroc"),
	MZ("MZ", "Mozambique"),
	MM("MM", "Birmanie"),
	NA("NA", "Namibie"),
	NR("NR", "Nauru"),
	NP("NP", "Népal"),
	NL("NL", "Pays-Bas"),
	NC("NC", "Nouvelle Calédonie"),
	NZ ("NZ", "Nouvelle-Zélande"),
	NI("NI", "Nicaragua"),
	NE("NE", "Niger"),
	NG("NG", "Nigéria"),
	NU("NU", "Nioué"),
	NF("NF", "Île Norfolk"),
	MP("MP", "Îles Mariannes du Nord"),
	NO("NO", "Norvège"),
	OM("OM", "Oman"),
	PK("PK", "Pakistan"),
	PW("PW", "Palaos"),
	PS("PS", "Palestine, État de"),
	PA("PA", "Panama"),
	PG("PG", "Papouasie-Nouvelle-Guinée"),
	PY("PY", "Paraguay"),
	PE("PE", "Pérou"),
	PH("PH", "Philippines"),
	PN("PN", "Pitcairn"),
	PL("PL", "Pologne"),
	PT("PT", "Portugal"),
	PR("PR", "Porto Rico"),
	QA("QA", "Qatar"),
	RE("RE", "Réunion"),
	RO("RO", "Roumanie"),
	RU("RU", "Fédération de Russie"),
	RW("RW", "Rwanda"),
	BL("BL", "Saint Barthélemy"),
	SH("SH", "Sainte Hélène, Ascension et Tristan da Cunha"),
	KN("KN", "Saint-Kitts-et-Nevis"),
	LC("LC", "Sainte-Lucie"),
	MF("MF", "Saint Martin"),
	PM("PM", "Saint Pierre et Miquelon"),
	VC("VC", "Saint Vincent et les Grenadines"),
	WS("WS", "Samoa"),
	SM("SM", "Saint-Marin"),
	ST("ST", "Sao Tomé et Principe"),
	SA("SA", "Arabie Saoudite"),
	SN("SN", "Sénégal"),
	RS("RS", "Serbie"),
	SC("SC", "Seychelles"),
	SL("SL", "Sierra Leone"),
	SG("SG", "Singapour"),
	SX("SX", "Sint Maarten"),
	SK("SK", "Slovaquie"),
	SI("SI", "Slovénie"),
	SB("SB", "Îles Salomon"),
	SO("SO", "Somalie"),
	ZA("ZA", "Afrique du Sud"),
	GS("GS", "Géorgie du Sud et îles Sandwich du Sud"),
	SS("SS", "Soudan du Sud"),
	ES("ES", "Espagne"),
	LK("LK", "Sri Lanka"),
	SD("SD", "Soudan"),
	SR("SR", "Surinam"),
	SJ("SJ", "Svalbard et Jan Mayen"),
	SZ("SZ", "Swaziland"),
	SE("SE", "Suède"),
	CH("CH", "Suisse"),
	SY("SY", "République arabe syrienne"),
	TW("TW", "Taiwan, Province de Chine"),
	TJ("TJ", "Tadjikistan"),
	TZ("TZ", "Tanzanie, République-Unie de"),
	TH("TH", "Thaïlande"),
	TL("TL", "Timor oriental"),
	TG("TG", "Togo"),
	TK("TK", "Tokélaou"),
	TO("TO", "Tonga"),
	TT("TT", "Trinité-et-Tobago"),
	TN("TN", "Tunisie"),
	TR("TR", "Turquie"),
	TM("TM", "Turkménistan"),
	TC("TC", "Îles Turques et Caïques"),
	TV("TV", "Tuvalu"),
	UG("UG", "Ouganda"),
	UA("UA", "Ukraine"),
	AE("AE", "Emirats Arabes Unis"),
	GB("GB", "Royaume-Uni"),
	US("US", "États-Unis d'Amérique"),
	UM("UM", "Îles mineures éloignées des États-Unis"),
	UY("UY", "Uruguay"),
	UZ("UZ", "Ouzbékistan"),
	VU("VU", "Vanuatu"),
	VE("VE", "Venezuela"),
	VN("VN", "Vietnam"),
	VG("VG", "Iles Vierges"),
	VI("VI", "Îles Vierges"),
	WF("WF", "Wallis et Futuna"),
	EH("EH", "Sahara occidental"),
	YE("YE", "Yémen"),
	ZM("ZM", "Zambie"),
	ZW("ZW", "Zimbabwe");
	
	private static final Map<String, Nationalite> codeMap = new HashMap<>();
	private static final Map<String, Nationalite> nomMap = new HashMap<>();
	
	private String code;
	private String nom;
	
	static {
		for (Nationalite n : values()) {
			codeMap.put(n.code, n);
			nomMap.put(n.nom, n);
		}
	}
	
	private Nationalite(String c, String n) {
		this.code = c;
		this.nom = n;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Permet d'obtenir le nom d'un pays a partir de son code a 2 chiffres
	 * @param code
	 * @return nom d'un pays
	 */
	public static Nationalite getByCode(String code) {
		return codeMap.get(code);
	}
	
	/**
	 * Permet d'obtenir le code d'un pays a partir de son nom
	 * @param nom
	 * @return code d'un pays
	 */
	public static Nationalite getByNom(String nom) {
		return nomMap.get(nom);
	}
	
	public static String[] getAllNationalites() {
		List<String> tempRes = new ArrayList<String>();
		for (Nationalite n : Nationalite.values()) {
			tempRes.add(n.getNom());
		}
		Collections.sort(tempRes);
		String[] res = new String[tempRes.size()];
		for (int i=0; i<tempRes.size(); i++) {
			res[i] = tempRes.get(i);
		}
		return res;
	}
	
}


