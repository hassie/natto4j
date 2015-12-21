package natto4j;

import jnr.ffi.Pointer;
import jnr.ffi.annotations.Direct;
import jnr.ffi.annotations.Encoding;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;

public interface Binding {
    // Model interface
    Pointer mecab_model_new2(
            String optsStr
    );

    void mecab_model_destroy(Pointer mptr);

    Pointer mecab_model_new_tagger(Pointer mptr);

    Pointer mecab_model_new_lattice(Pointer mptr);

    DictionaryInfo mecab_model_dictionary_info(Pointer mptr);

    // Tagger interface
    void mecab_destroy(Pointer tptr);

    String mecab_version();

    String mecab_strerror(Pointer tptr);

    String mecab_format_node(Pointer tptr, Pointer nptr);

    // Lattice interface
    void mecab_lattice_destroy(Pointer lptr);

    void mecab_lattice_clear(Pointer lptr);

    int mecab_lattice_is_available(Pointer lptr);

    String mecab_lattice_strerror(Pointer lptr);

    String mecab_lattice_get_sentence(Pointer lptr);

    void mecab_lattice_set_sentence(
            Pointer lptr,
            @Encoding("UTF-8") String sentence
    );

    int mecab_lattice_get_size(Pointer lptr);

    void mecab_lattice_set_theta(Pointer lptr, float theta);

    void mecab_lattice_set_z(Pointer lptr, float z);

    int mecab_lattice_get_request_type(Pointer lptr);

    void mecab_lattice_add_request_type(Pointer lptr, int rtype);

    void mecab_lattice_set_request_type(Pointer lptr, int rtype);

    int mecab_lattice_get_boundary_constraint(Pointer lptr, int pos);

    void mecab_lattice_set_boundary_constraint(Pointer lptr, int pos, int btype);

    String mecab_lattice_get_feature_constraint(Pointer lptr, int bpos);

    void mecab_lattice_set_feature_constraint(Pointer lptr, int bpos, int epos, String feature);

    int mecab_parse_lattice(Pointer tptr, Pointer lptr);

    int mecab_lattice_next(Pointer lptr);

    String mecab_lattice_tostr(Pointer lptr);
    //jnr.ffi.Struct.String mecab_lattice_tostr(Pointer lptr);

    String mecab_lattice_nbest_tostr(Pointer lptr, int n);

    Pointer mecab_lattice_get_bos_node(Pointer lptr);
}
