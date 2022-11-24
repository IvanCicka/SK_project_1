/**
 * 
 */
package raf.storage.component.spec;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 
 * Interface for handling operations with file storages. All paths must be
 * relative paths.
 * 
 * @see /storage-handler/src/raf/storage/component/spec/Storage.java
 *
 */
/**
 * @author raf.sk
 *
 */
public interface FileStorageHandler {

	/**
	 * @param parentDirectory parent directory, if null then storage root directory
	 *                        is default
	 * @param path            path of new directory
	 * @return boolean
	 */
	public boolean createDirectory(File parentDirectory, String path);

	/**
	 * @param files        list of files to be moved
	 * @param relativePath should be contained in storage in which this method is
	 *                     invoked
	 * @return boolean
	 */
	public boolean moveFiles(List<File> files, String relativePath);

	/**
	 * @param parentDirectory directory holding the file or directory to be deleted
	 * @param toDelete        file or directory to delete
	 * @return
	 */
	public boolean deleteFileOrDir(File parentDirectory, File toDelete);

	/**
	 * Moves all files from source to destination directory
	 * 
	 * @param source      directory from which all files will be moved
	 * @param destination directory in which files will be moved
	 * @return
	 */
	public boolean moveFilesDirToDir(File source, File destination);

	/**
	 * @param destinationPath should not be included in the storage
	 * @return File downloaded file from storage, null if can't be downloaded
	 */
	public File downloadFileFromStorage(String destinationPath);

	/**
	 * @param file    file to be renamed
	 * @param newName new name for the file
	 */
	public void rename(File file, String newName);

	/**
	 * @return Representation of storage - string containing names of files,
	 *         directories and metadata.
	 */
	public String getAllFilesData();

	/**
	 * @param directory             from which all files and directories will be
	 *                              returned
	 * @param includeSubDirectories if true, all subdirectories will be included
	 * @return
	 */
	public List<File> getAllFiles(File directory, boolean includeSubDirectories);

	/**
	 * @param extension should be in {.extension} or {extension} format, no special
	 *                  characters
	 * @return list of files with given extension
	 */
	public List<File> getFilesWithExtension(String extension);

	/**
	 * @param subString string for searching files with similar name
	 * @return files which contain given string in filename
	 */
	public List<File> searchFilesWithSubString(String subString);

	/**
	 * @param filenames
	 * @return boolean, true if any of given file names are contained in the storage
	 */
	public boolean checkFileNamesContained(List<String> filenames);

	/**
	 * @param filename name of the file to be searched for
	 * @return directory containing the file with given name, null if the storage
	 *         does not contain file with given name
	 */
	public File getDirectoryContainer(String filename);

	/**
	 * @param byName        set sorting by name
	 * @param byDateCreated set sorting by date created
	 * @param ascending     set ascending or descending order(false)
	 */
	public void setSortingCriterium(boolean byName, boolean byDateCreated, boolean ascending);

	/**
	 * @param directory to be searched for created or modified files
	 * @param start     beginning date
	 * @param end       ending date
	 */
	public void getFilesFromModifiedPeriod(File directory, Date start, Date end);

	/**
	 * @param pathFromRoot       if true, path will be shown from root
	 * @param onlyName           if true only name will be displayed
	 * @param sizeAndDateCreated if true size and date created info will be
	 *                           displayed
	 */
	public void filterSearch(boolean pathFromRoot, boolean onlyName, boolean sizeAndDateCreated);
}
