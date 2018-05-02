package com.nte.realtime;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StreamRequest {

	public final static class OPERATING_MODE {

		public final static String ACCURATE = "accurate";

		public final static String FAST = "fast";

		public final static String WARP = "warp";
	}

	/**
	 * The timeout period, in seconds. After this time, the streamed job terminates. 
	 * The default is set in the configuration file. See Configure streaming timeout.
	 * default value is 30
	 */
	@JSONField(name = "inactivity_timeout")
	private int inactivityTimeout = 10;

	/**
	 * Locale code string. Mandatory. Must align with the language pack
	 * default value is "zho-chn"
	 */
	private Model model = new Model("zho-CHN");

	private Map<String, Channel> channels = new HashMap<>();

	/**
	 * Job identifier. Spaces are not allowed. Valid characters are: A-Z a-z 0-9 . - _ [] { } Optional. 
	 * If not specified, a uuid is created as a job identifier.
	 */
	private String reference = UUID.randomUUID().toString().replace("-", "");

	/**
	 * Keyword string. The accuracy/speed setting. Mandatory.
	 * “accurate”: Most accurate transcription, may be slow.
	 * “fast”: Balance of speed and transcription accuracy.
	 * “warp”: Fastest transcription time, may be less accurate.
	 * default value is "fast"
	 */
	@JSONField(name = "operating_mode")
	private String operatingMode = OPERATING_MODE.FAST;

	public int getInactivityTimeout() {
		return inactivityTimeout;
	}

	public Model getModel() {
		return model;
	}

	public String getReference() {
		return reference;
	}

	public String getOperatingMode() {
		return operatingMode;
	}

	public StreamRequest addChannel(Channel channel) {
		this.channels.put(channel.getName(), channel);
		return this;
	}

	public Map<String, Channel> getChannels() {
		return channels;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Model {
		/**
		 * Locale code string. Mandatory. Must align with the language pack, for example “eng-usa”.
		 */
		private String name;

		// private Domain domain = new
		// Domain("heguang_5ccd0ee0-a3ff-11e7-852e-8995224aba0d",
		// "http://47.92.106.197:8200/domainLM/heguang_5ccd0ee0-a3ff-11e7-852e-8995224aba0d",
		// "high");
	}

	public static class Domain {

		private String name;

		private String url;

		private String weight;

		public Domain(String name, String url, String weight) {
			super();
			this.name = name;
			this.url = url;
			this.weight = weight;
		}

	}

	public static class Channel {

		/**
		 * Channel label String
		 */
		@JSONField(serialize = false)
		private String name;

		/**
		 * Audio format. Wave and other common audio MIME formats are accepted. Mandatory.	See Audio formats for details.
		 * 
		 * 
		 * "format": "audio/wave" Wave file with a RIFF header containing rate and channel information
		 *	
		 *	"format": "audio/pcma; rate=16000; channels=2" a-law, stereo, 16 kHz
			"format": "audio/PCMA; channels=2" a-law, stereo, 8 kHz
		 *	"format": "audio/x-alaw-basic; rate=16000; channels=2"
			"format": "audio/L16" 16-bit headerless PCM file, mono, 8 kHz (mono, 8 kHz are the defaults for this format)
			"format": "audio/L16; rate=16000" 16-bit headerless PCM file, mono, 16 kH.The default sample rate is overridden by,rate=16000
			"format": "audio/L16; rate=16000; channels=2" 16-bit headerless PCM file, stereo, 16 kHz
		
		 */
		private String format;

		/**
		 * Keyword string. How results will be returned. Optional.
		 * 
		 * “lattice” (default): Individual words as time-indexed spoken-form tokens. The result
		 *	includes all words recognized, with the best choice indicated as “best_path”:true.
		 *
		 * “transcript”: A phrase-based representation of the discourse in written form. Only the
		 *	best path is returned, for example: “I live at 123 Main Street.”
		 *	This entry is enabled by your licensing agreement and language pack. When disabled,
		 *	the lattice format is returned
		 */
		@JSONField(name = "result_format")
		private String resultFormat;

		/**
		 * Whether the results include standard or enhanced information:
		 * 1 (default): Standard information is included in the transcription result.
		 * 2: Enhanced information is provided. See Result version.
		 *
		 */
		@JSONField(name = "result_version")
		private int resultVersion;

		public Channel(String name, String format) {
			this(name, format, "lattice", 1);
		}

		public Channel(String name, String format, String resultFormat) {
			this(name, format, resultFormat, 1);
		}

		public Channel(String name, String format, String resultFormat, int resultVersion) {
			super();
			this.name = name;
			this.format = format;
			this.resultFormat = resultFormat;
			this.resultVersion = resultVersion;
		}

		public String getName() {
			return name;
		}

		public String getFormat() {
			return format;
		}

		public String getResultFormat() {
			return resultFormat;
		}

		public int getResultVersion() {
			return resultVersion;
		}
	}

	public static void main(String[] args) {
		StreamRequest request = new StreamRequest();
		request.addChannel(new StreamRequest.Channel("啥玩意儿啊", "audio/wav"))
				.addChannel(new StreamRequest.Channel("啥玩意儿啊123", "audio/wav"));
		System.out.println(JSON.toJSONString(request));
	}
}