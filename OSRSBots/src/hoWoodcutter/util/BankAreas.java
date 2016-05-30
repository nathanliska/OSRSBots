package hoWoodcutter.util;

import org.dreambot.api.methods.map.Area;

public enum BankAreas {
	
	BANK_VARROCK_EAST("Varrock East", new Area()),
	BANK_VARROCK_WEST("Varrock West", new Area()),
	BANK_DRAYNOR("Draynor", new Area(3092, 3240, 3097, 3246, 0)),
	BANK_EDGEVILLE("Edgeville", new Area(3092, 3489, 3097, 3495, 0));
	
	//COMMENTED FOR TESTING.
	
	/*
	BANK_DRAYNOR(new Area(3088, 3239, 3098, 3247, 0)),
	BANK_FALADOR_EAST(new Area(3008, 3352, 3019, 3359, 0)),
	BANK_AL_KHARID(new Area(3268, 3161, 3273, 3174, 0)),
	BANK_EDGEVILLE(new Area(3090, 3488, 3100, 3500, 0)),
	BANK_CATHERBY(new Area(2805, 3433, 2816, 3446, 0)),
	BANK_CATHERBY_SHOP(new Area(2831, 3441, 2837, 3447, 0)),
	BANK_SHILO_VILLAGE(new Area(2845, 2950, 2860, 2960, 0)),
	BANK_PICATORIS(new Area(2320, 3680, 2320, 3700, 0)),
	BANK_BARBARIAN_FISHING(new Area(2500, 3487, 2503, 3491, 0)),
	BANK_ARDOUGNE(new Area(2610, 3325, 2625, 3335, 0)),
	BANK_JATIZSO(new Area(2410, 3795, 2420, 3805, 0)),
	BANK_RELLEKKA_SHOPS(new Area(2630, 3670, 2640, 3685, 0)),
	BANK_BARBARIAN_OUTPOST(new Area(2525, 3555, 2540, 3580, 0)),
	BANK_SEERS(new Area(2720, 3490, 2730, 3500, 0)),
	*/
	
	private final String stringArea;
	private final Area area;
	
	BankAreas(String stringArea, Area area) {
		this.stringArea = stringArea;
		this.area = area;
	}
	
	public String getAreaName() {
		return stringArea;
	}
	
	public Area getArea() {
		return area;
	}
}
