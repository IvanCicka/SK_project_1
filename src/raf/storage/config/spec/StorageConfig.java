package raf.storage.config.spec;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * This class is abstract data model for configuration of the storage.
 *
 */
public abstract class StorageConfig {

	/**
	 * size in bytes
	 */
	protected long size;
	/**
	 * maximum number of files contained by the storage
	 */
	protected int maxFilesNum;
	/**
	 * files with this extensions are not added to the storage
	 */
	protected List<String> forbiddenExtensions;

	public StorageConfig() {
		forbiddenExtensions = new LinkedList();
		addDefaultForbiddenExtensions();
	}

	/**
	 * Default hard-coded extensions
	 */
	private void addDefaultForbiddenExtensions() {
		forbiddenExtensions.add(".exe");
		forbiddenExtensions.add(".bat");
		forbiddenExtensions.add(".sh");
	}

	public List<String> getForbiddenExtensions() {
		return forbiddenExtensions;
	}

	public void setForbiddenExtensions(List<String> forbiddenExtensions) {
		this.forbiddenExtensions = forbiddenExtensions;
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(String.valueOf(size));
		stb.append(" ");
		stb.append(String.valueOf(maxFilesNum));
		stb.append(" ");
		for (int i = 0; i < forbiddenExtensions.size(); i++) {
			stb.append("." + forbiddenExtensions.get(i));
			stb.append(" ");
		}
		return stb.toString();
	}

}
