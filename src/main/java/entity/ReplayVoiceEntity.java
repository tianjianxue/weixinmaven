package entity;

import java.util.HashMap;
import java.util.Map;

public class ReplayVoiceEntity extends MessageEntity {

	public ReplayVoiceEntity() {
		super();
		super.setMsgType("voice");
	}

	private ReplayVoiceMediaEntity Voice;

	public ReplayVoiceMediaEntity getVoice() {
		return Voice;
	}

	public void setVoice(ReplayVoiceMediaEntity voice) {
		Voice = voice;
	}
	


	
}
