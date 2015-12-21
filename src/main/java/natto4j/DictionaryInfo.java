package natto4j;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by hassie on 2015/12/01.
 */
public class DictionaryInfo extends jnr.ffi.Struct{
    /**
     * filename of dictionary
     * On Windows, filename is stored in UTF-8 encoding
     */
    public final String filename = new UTF8StringRef();  // const char *filename;

    /**
     * character set of the dictionary. e.g., "SHIFT-JIS", "UTF-8"
     */
    public final String charset = new UTF8StringRef(); // const char *charset;

    /**
     * How many words are registered in this dictionary.
     */
    public final Unsigned32 size = new Unsigned32(); // unsigned int

    /**
     * dictionary type
     * this value should be MECAB_USR_DIC, MECAB_SYS_DIC, or MECAB_UNK_DIC.
     */
    public final Signed32 type = new Signed32(); // int

    /**
     * left attributes size
     */
    public final Unsigned16 lsize = new Unsigned16(); // unsigned int

    /**
     * right attributes size
     */
    public final Unsigned32 rsize = new Unsigned32(); // unsigned int

    /**
     * version of this dictionary
     */
    public final Unsigned16 version = new Unsigned16(); // unsigned short

    /**
     * pointer to the next dictionary info.
     */
    public final Pointer next = new Pointer(); // struct mecab_dictionary_info_t *next;



    public DictionaryInfo(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    private enum DictionaryType {
        SYS_DIC(0), // System dictionary
        USR_DIC(1), // User dictionary
        UNK_DIC(2); // Unknown dictionary.

        private DictionaryType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        private final int id;
    }


    /**
     * @return [Path] Absolute filepath to MeCab dictionary.
     */
    public Path getFilePath() {
        Path p = Paths.get(getFileName());
        return p.normalize().toAbsolutePath();
    }

    private java.lang.String getFileName() {
        return filename.toString();
    }

    public java.lang.String getCharset() {
        return charset.toString();
    }

    public long getSize() {
        return size.longValue();
    }

    public int getType() {
        return type.intValue();
    }

    public int getVersion() {
        return version.intValue();
    }

    /**
     * Returns `true` if this is a system dictionary.
     *
     * @return boolean
     */
    public boolean isSysDic() {
        return type.intValue() == DictionaryType.SYS_DIC.getId();
    }

    /**
     * Returns `true` if this is a user dictionary.
     *
     * @return boolean
     */
    public boolean isUsrDic() {
        return type.intValue() == DictionaryType.USR_DIC.getId();
    }

    /**
     * Returns `true` if this is a unknown dictionary type.
     *
     * @return boolean
     */
    public boolean isUnkDic() {
        return type.intValue() == DictionaryType.UNK_DIC.getId();
    }

    /**
     * implment me!
     * @return String
     */
    @Override
    public java.lang.String toString() {
        return super.toString();
    }


}
