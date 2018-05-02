package com.nte.realtime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class StreamResponse {
	
	private String status;
	
	private String reference;
	
	private Integer sequence;
	
	@JSONField(name="operating_mode")
	private String operatingMode;
	
	private Map<String,Channel> channels = new HashMap<>();
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getOperatingMode() {
		return operatingMode;
	}

	public void setOperatingMode(String operatingMode) {
		this.operatingMode = operatingMode;
	}

	public Map<String, Channel> getChannels() {
		return channels;
	}

	public void setChannels(Map<String, Channel> channels) {
		this.channels = channels;
	}

	public static class Channel{
		
		private List<Object> errors;
		
		private Map<String,Lattice> lattice = new HashMap<>();
		
		@JSONField(name="partial_lattice")
		private Map<String,Lattice> partialLattice = new HashMap<>();

		public List<Object> getErrors() {
			return errors;
		}

		public void setErrors(List<Object> errors) {
			this.errors = errors;
		}

		public Lattice getLattice() {
			return lattice.get("1");
		}

		public void setLattice(Map<String, Lattice> lattice) {
			this.lattice = lattice;
		}

		public Lattice getPartialLattice() {
			return partialLattice.get("1");
		}

		public void setPartialLattice(Map<String, Lattice> partialLattice) {
			this.partialLattice = partialLattice;
		}

		@Override
		public String toString() {
			return "Channel [errors=" + errors + ", lattice=" + lattice + ", partialLattice=" + partialLattice + "]";
		}
		
	}
	
	public static class Lattice{
		private Map<String,Link> links = new HashMap<>();

		public Map<String, Link> getLinks() {
			return links;
		}

		public void setLinks(Map<String, Link> links) {
			this.links = links;
		}
		
		public List<Link> links(){
			return new ArrayList<>(links.values());
		}

		@Override
		public String toString() {
			return "Lattice [links=" + links + "]";
		}
	}
	
	public static class Link{
		private double start;
		
		private double end;
		
		private double weight;
		
		@JSONField(name="best_path")
		private boolean bestPath;
		
		private String word;
		
		private double intensity;
		
		private String text;

		public double getStart() {
			return start;
		}

		public void setStart(double start) {
			this.start = start;
		}

		public double getEnd() {
			return end;
		}

		public void setEnd(double end) {
			this.end = end;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}

		public boolean isBestPath() {
			return bestPath;
		}

		public void setBestPath(boolean bestPath) {
			this.bestPath = bestPath;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public double getIntensity() {
			return intensity;
		}

		public void setIntensity(double intensity) {
			this.intensity = intensity;
		}
		
		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
	
	@Override
	public String toString() {
		return "StreamResponse [status=" + status + ", reference=" + reference + ", sequence=" + sequence
				+ ", operatingMode=" + operatingMode + ", channels=" + channels + "]";
	}

	public static void main(String[] args) throws IOException {
		InputStream is = StreamResponse.class.getResourceAsStream("test.json");
		int len = -1;
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while((len=is.read(buffer, 0, 1024))>0){
			baos.write(buffer, 0, len);
		}
		is.close();
		String json = new String(baos.toByteArray(),"UTF-8");
		StreamResponse response = JSON.parseObject(json, StreamResponse.class);
		Channel channel = response.getChannels().get("test-001");
		System.out.println(channel.getLattice().links());
		System.out.println(channel.getPartialLattice().links());
		System.out.println(channel);
		System.out.println(response);
	}
}


