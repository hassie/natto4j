package natto4j;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.types.pid_t;

import java.util.ArrayList;

/**
 * Created by hassie on 2015/12/02.
 */
public class Natto {
    public enum LaticeLevel {
        MECAB_LATTICE_ONE_BEST(1),
        MECAB_LATTICE_NBEST(2),
        MECAB_LATTICE_PARTIAL(4),
        MECAB_LATTICE_MARGINAL_PROB(8),
        MECAB_LATTICE_ALTERNATIVE(16),
        MECAB_LATTICE_ALL_MORPHS(32),
        MECAB_LATTICE_ALLOCATE_SENTENCE(64);

        private LaticeLevel(int id) { this.id = id; }

        public int getId() { return id; }

        private final int id;
    }

    public enum MecabBoundary {
        MECAB_ANY_BOUNDARY(0),
        MECAB_TOKEN_BOUNDARY(1),
        MECAB_INSIDE_TOKEN(2);

        private MecabBoundary(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        private final int id;
    }

    private MecabOption mecatOption;
    private Pointer model;
    private Pointer tagger;
    private Pointer lattice;
    private Binding mecabBinding;
    public Natto(){
        init();
    }

    public Natto(MecabOption mecabOption){
        this.mecatOption = mecabOption;
        //init();
    }

    private void init(){
//        //mecabBinding = LibraryLoader.create(Binding.class).load("/usr/local/Cellar/mecab/0.996/lib/libmecab.dylib");
//        mecabBinding = LibraryLoader.create(Binding.class).load("/usr/local/lib/libmecab.dylib");
//        model = mecabBinding.mecab_model_new2("-d /usr/corproid/mecab-dics/ipa/mecab-ipadic-neologd");
//        tagger = mecabBinding.mecab_model_new_tagger(model);
//        if (tagger.address() == 0x0) {
//            throw new IllegalStateException();
//        }
//        lattice = mecabBinding.mecab_model_new_lattice(model);
//        if (lattice.address() == 0x0) {
//            throw new IllegalStateException();
//        }
    }
    public String parse(String text){
        mecabBinding = LibraryLoader.create(Binding.class).load("/usr/local/lib/libmecab.dylib");
//      @model   = self.class.mecab_model_new2(opt_str) #<FFI::Pointer address=0x007fd9bb7ee130>
        model = mecabBinding.mecab_model_new2("-d /usr/corproid/mecab-dics/ipa/mecab-ipadic-neologd");

//      @tagger  = self.class.mecab_model_new_tagger(@model) #<FFI::Pointer address=0x007fd9bd753690>
        tagger = mecabBinding.mecab_model_new_tagger(model);

//      @lattice = self.class.mecab_model_new_lattice(@model)	#<FFI::Pointer address=0x007fd9bd68c300>
        lattice = mecabBinding.mecab_model_new_lattice(model);

//      @libpath = self.class.find_library #"/usr/local/Cellar/mecab/0.996/lib/libmecab.dylib"
//      self.mecab_lattice_set_request_type(@lattice, MECAB_LATTICE_ONE_BEST) # MECAB_LATTICE_ONE_BEST=1
        mecabBinding.mecab_lattice_set_request_type(lattice, 1);

//      self.mecab_model_dictionary_info(@model)
        DictionaryInfo d = mecabBinding.mecab_model_dictionary_info(model);

//      self.mecab_lattice_set_sentence(@lattice, text)
        mecabBinding.mecab_lattice_set_sentence(lattice, text);
        String sentence = mecabBinding.mecab_lattice_get_sentence(lattice);
        System.out.println(sentence);
//        self.mecab_parse_lattice(@tagger, @lattice)
//        retval = self.mecab_lattice_tostr(@lattice) # retval.encoding="ASCII-8BIT"
        mecabBinding.mecab_parse_lattice(tagger, lattice);

        String result = mecabBinding.mecab_lattice_tostr(lattice);
        System.out.println("-----------------------------");
        return result;
    }
    public static void main(String[] args) throws Exception{
        Natto natto = new Natto();
        String result = natto.parse("headius, help me!");
        System.out.println(result);
    }
}


