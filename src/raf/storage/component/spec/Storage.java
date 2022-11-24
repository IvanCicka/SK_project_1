package raf.storage.component.spec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import raf.storage.config.spec.DefaultConfig;
import raf.storage.config.spec.StorageConfig;

/**
 * @author raf.sk
 *
 */
public abstract class Storage {

	private static final String DEFAULT_PATH = "/";
	/**
	 * Configuration class for Storage
	 */
	protected StorageConfig storageConfig;
	protected File rootDirectory;
	protected List<File> subDirectories;

	/**
	 * Sets default configuration.
	 */
	public Storage() {
		storageConfig = new DefaultConfig();
	}

	/**
	 * @param path @NotNull
	 */
	public Storage(String path) {
		this();
		rootDirectory = new File(path);
		if (rootDirectory.mkdir() == true) {
			System.out.println("Storage has been created successfully");
		} else {
			System.out.println("Storage cannot be created");
		}
	}

	/**
	 * @param path             : @NotNull Relative path for creating file
	 *                         storage(folder)
	 * @param theStorageConfig : Configuration for file storage, overrides default
	 *                         configuration
	 */
	public Storage(String path, StorageConfig theStorageConfig) {
		this(path);
		if (null != theStorageConfig) {
			this.setStorageConfig(theStorageConfig);
		}
	}

	/**
	 * @param file : File to be parsed for .extensions strings. Can be different for
	 *             different implementations.
	 * @return boolean: TRUE - reading from file succeeded FALSE - reading did not
	 *         succeed
	 */
	public abstract boolean setForbiddenExtensions(File file);

	/**
	 * @param extensions : string list which represents forbidden file extensions
	 */
	public void setForbiddenExtensions(List<String> extensions) {
		this.storageConfig.setForbiddenExtensions(extensions);
	}

	/**
	 * @param extension : string of file extension to be added to forbidden
	 */
	public void addForbiddenExtensions(String extension) {
		this.storageConfig.getForbiddenExtensions().add(extension);
	}

	public StorageConfig getStorageConfig() {
		return storageConfig;
	}

	public void setStorageConfig(StorageConfig storageConfig) {
		this.storageConfig = storageConfig;
	}

	private void saveConfig(String path) {
		File configFile = new File(rootDirectory, path);
		try {
			PrintWriter writer = new PrintWriter(configFile);
			writer.write(storageConfig.toString());
		} catch (FileNotFoundException e) {
			System.out.print("Configuration file can't be saved.");
		}
		subDirectories.add(configFile);
	}

	/**
	 * Utility method
	 * 
	 * @param source File to be copied
	 * @param dest   Empty file for putting copied data
	 */
	private static void copyFileUsingStream(File source, File dest) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

		} catch (IOException e) {
			System.out.println("Files can't be copied.");
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				System.out.println("Files can't be copied.");
			}

		}
	}

}
