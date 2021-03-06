package fr.utarwyn.superjukebox.nbs;

/**
 * Represents an exception during
 * the decoding process of a NBS file.
 *
 * @author Utarwyn <maxime.malgorn@laposte.net>
 * @since 0.3.0
 */
public class NBSDecodeException extends Exception {

	public NBSDecodeException(String filename, String message, Exception ex) {
		super("We can't decode the music file " + filename + ". " + message, ex);
	}

}
