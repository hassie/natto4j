package natto4j;


/**
 * `MeCabNode` is a wrapper for the `struct mecab_node_t`
 * structure holding the parsed `node`.
 *
 * Values for the MeCab node attributes may be
 * obtained by using the following `Symbol`s as keys
 * to the layout associative array of `FFI::Struct` members.
 *
 * - :prev - pointer to previous node
 * - :next - pointer to next node
 * - :enext - pointer to the node which ends at the same position
 * - :bnext - pointer to the node which starts at the same position
 * - :rpath - pointer to the right path; nil if MECAB_ONE_BEST mode
 * - :lpath - pointer to the right path; nil if MECAB_ONE_BEST mode
 * - :surface - surface string; length may be obtained with length/rlength members
 * - :feature - feature string
 * - :id - unique node id
 * - :length - length of surface form
 * - :rlength - length of the surface form including white space before the morph
 * - :rcAttr - right attribute id
 * - :lcAttr - left attribute id
 * - :posid - part-of-speech id
 * - :char_type - character type
 * - :stat - node status; 0 (NOR), 1 (UNK), 2 (BOS), 3 (EOS), 4 (EON)
 * - :isbest - 1 if this node is best node
 * - :alpha - forward accumulative log summation, only with marginal probability flag
 * - :beta - backward accumulative log summation, only with marginal probability flag
 * - :prob - marginal probability, only with marginal probability flag
 * - :wcost - word cost
 * - :cost - best accumulative cost from bos node to this node
 *
 * ## Usage
 * An instance of `MeCabNode` is yielded to the block
 * used with `MeCab#parse`, where the above-mentioned
 * node attributes may be accessed by name.
 *
 *     nm = Natto::MeCab.new
 *
 *     nm.parse('卓球なんて死ぬまでの暇つぶしだよ。') do |n|
 *       puts "#{n.surface}\t#{n.cost}" if n.is_nor?
 *     end
 *     卓球     2874
 *     なんて    4398
 *     死ぬ     9261
 *     まで     9386
 *     の       10007
 *     暇つぶし 13324
 *     だ       15346
 *     よ       14396
 *     。       10194
 *
 * While it is also possible to use the `Symbol` for the
 * MeCab node member to index into the
 * `FFI::Struct` layout associative array, please use the attribute
 * accessors. In the case of `:surface` and `:feature`, MeCab
 * returns the raw bytes, so `natto` will convert that into
 * a string using the default encoding.
 class MeCabNode < MeCabStruct
 # @return [String] surface morpheme surface value.
 attr_accessor :surface
 # @return [String] corresponding feature value.
 attr_accessor :feature
 # @return [FFI::Pointer] pointer to MeCab node struct.
 attr_reader   :pointer

 # Normal MeCab node defined in the dictionary, c.f. `stat`.
 NOR_NODE = 0
 # Unknown MeCab node not defined in the dictionary, c.f. `stat`.
 UNK_NODE = 1
 # Virtual node representing the beginning of the sentence, c.f. `stat`.
 BOS_NODE = 2
 # Virutual node representing the end of the sentence, c.f. `stat`.
 EOS_NODE = 3
 # Virtual node representing the end of an N-Best MeCab node list, c.f. `stat`.
 EON_NODE = 4

 layout  :prev,            :pointer,
 :next,            :pointer,
 :enext,           :pointer,
 :bnext,           :pointer,
 :rpath,           :pointer,
 :lpath,           :pointer,
 :surface,         :string,
 :feature,         :string,
 :id,              :uint,
 :length,          :ushort,
 :rlength,         :ushort,
 :rcAttr,          :ushort,
 :lcAttr,          :ushort,
 :posid,           :ushort,
 :char_type,       :uchar,
 :stat,            :uchar,
 :isbest,          :uchar,
 :alpha,           :float,
 :beta,            :float,
 :prob,            :float,
 :wcost,           :short,
 :cost,            :long

 # Initializes this node instance.
 # Sets the MeCab feature value for this node.
 # @param nptr [FFI::Pointer] pointer to MeCab node
 def initialize(nptr)
 super(nptr)
 @pointer = nptr

 if self[:feature]
 @feature = self[:feature].force_encoding(Encoding.default_external)
 end
 end

 # Returns human-readable details for the MeCab node.
 # Overrides `Object#to_s`.
 #
 # - encoded object id
 # - underlying FFI pointer to MeCab Node
 # - stat (node type: NOR, UNK, BOS/EOS, EON)
 # - surface
 # - feature
 # @return [String] encoded object id, underlying FFI pointer, stat, surface, and feature
 def to_s
 [ super.chop,
 "@pointer=#{@pointer},",
 "stat=#{self[:stat]},",
 "@surface=\"#{self.surface}\",",
 "@feature=\"#{self.feature}\">" ].join(' ')
 end

 # Overrides `Object#inspect`.
 # @return [String] encoded object id, stat, surface, and feature
 # @see #to_s
 def inspect
 self.to_s
 end

 # Returns `true` if this is a normal MeCab node found in the dictionary.
 # @return [Boolean]
 def is_nor?
 self.stat == NOR_NODE
 end

 # Returns `true` if this is an unknown MeCab node not found in the dictionary.
 # @return [Boolean]
 def is_unk?
 self.stat == UNK_NODE
 end

 # Returns `true` if this is a virtual MeCab node representing the beginning of the sentence.
 # @return [Boolean]
 def is_bos?
 self.stat == BOS_NODE
 end

 # Returns `true` if this is a virtual MeCab node representing the end of the sentence.
 # @return [Boolean]
 def is_eos?
 self.stat == EOS_NODE
 end

 # Returns `true` if this is a virtual MeCab node representing the end of the node list.
 # @return [Boolean]
 def is_eon?
 self.stat == EON_NODE
 end
 end
 end*
 * Created by hassie on 2015/12/01.
 */
public class MecabNode extends jnr.ffi.Struct {
    public enum NodeType {
        NOR_NODE(0), // Normal MeCab node defined in the dictionary, c.f. `stat`.
        UNK_NODE(1), // Unknown MeCab node not defined in the dictionary, c.f. `stat`.
        BOS_NODE(2), // Virtual node representing the beginning of the sentence, c.f. `stat`.
        EOS_NODE(3), // Virutual node representing the end of the sentence, c.f. `stat`.
        EON_NODE(4); // Virtual node representing the end of an N-Best MeCab node list, c.f. `stat`.

        private NodeType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        private final int id;
    }

    /**
     * pointer to the previous node.
     */
    private Pointer prev = new Pointer();

    /**
     * pointer to the next node.
     */
    private Pointer next = new Pointer();

    /**
     * pointer to the node which ends at the same position.
     */
    private Pointer enext = new Pointer();

    /**
     * pointer to the node which starts at the same position.
     */
    private Pointer bnext = new Pointer();

    /**
     * pointer to the right path.
     * this value is NULL if MECAB_ONE_BEST mode.
     */
    private Pointer rpath = new Pointer();

    /**
     * pointer to the right path.
     * this value is NULL if MECAB_ONE_BEST mode.
     */
    private Pointer lpath = new Pointer();

    /**
     * surface string.
     * this value is not 0 terminated.
     * You can get the length with length/rlength members.
     */
    private String surface = new UTF8StringRef();

    /**
     * feature string
     */
    private String feature = new UTF8StringRef();

    /**
     * unique node id
     */
    private Unsigned32 id = new Unsigned32();

    /**
     * length of the surface form.
     */
    private Unsigned16 length = new Unsigned16();

    /**
     * length of the surface form including white space before the morph.
     */
    private Unsigned16 rlength = new Unsigned16();

    /**
     * right attribute id
     */
    private Unsigned16 rcAttr = new Unsigned16();

    /**
     * left attribute id
     */
    private Unsigned16 lcAttr = new Unsigned16();

    /**
     * unique part of speech id. This value is defined in "pos.def" file.
     */
    private Unsigned16 posid  = new Unsigned16();

    /**
     * character type
     */
    private Unsigned8 char_type  = new Unsigned8();

    /**
     * status of this model.
     * This value is MECAB_NOR_NODE, MECAB_UNK_NODE, MECAB_BOS_NODE, MECAB_EOS_NODE, or MECAB_EON_NODE.
     */
    private Unsigned8 stat  = new Unsigned8();

    /**
     * set 1 if this node is best node.
     */
    private Unsigned8 isbest  = new Unsigned8();

    /**
     * forward accumulative log summation.
     * This value is only available when MECAB_MARGINAL_PROB is passed.
     */
    private Float alpha = new Float();

    /**
     * backward accumulative log summation.
     * This value is only available when MECAB_MARGINAL_PROB is passed.
     */
    private Float beta = new Float();

    /**
     * marginal probability.
     * This value is only available when MECAB_MARGINAL_PROB is passed.
     */
    private Float prob = new Float();

    /**
     * word cost.
     */
    private Signed16 wcost = new Signed16();

    /**
     * best accumulative cost from bos node to this node.
     */
    private SignedLong cost = new SignedLong();

    public MecabNode(jnr.ffi.Runtime runtime){
        super(runtime);
    }

    public Pointer getPrev() {
        return prev;
    }

    public Pointer getNext() {
        return next;
    }

    public Pointer getEnext() {
        return enext;
    }

    public Pointer getBnext() {
        return bnext;
    }

    public Pointer getRpath() {
        return rpath;
    }

    public Pointer getLpath() {
        return lpath;
    }

    public java.lang.String getSurface() {
        return surface.toString();
    }

    public java.lang.String getFeature() {
        return feature.toString();
    }

    public int getId() {
        return id.intValue();
    }

    public int getLength() {
        return length.intValue();
    }

    public int getRlength() {
        return rlength.intValue();
    }

    public int getRightCostAttr() {
        return rcAttr.intValue();
    }

    public int getLeftCostAttr() {
        return lcAttr.intValue();
    }

    public int getPosId() {
        return posid.intValue();
    }

    public int getCharType() {
        return char_type.intValue();
    }

    public int getStat() {
        return stat.intValue();
    }

    public int getIsbest() {
        return isbest.intValue();
    }

    public float getAlpha() {
        return alpha.floatValue();
    }

    public float getBeta() {
        return beta.floatValue();
    }

    public float getProb() {
        return prob.floatValue();
    }

    public int getWcost() {
        return wcost.intValue();
    }

    public long getCost() {
        return cost.longValue();
    }

    /**
     * Returns `true` if this is a normal MeCab node found in the dictionary.
     * @return [boolean]
     */
    public boolean isNor(){
        return NodeType.NOR_NODE.getId() == getStat();
    }

    /**
     * Returns `true` if this is an unknown MeCab node not found in the dictionary.
     * @return [boolean]
     */
    public boolean isUNK(){
        return NodeType.UNK_NODE.getId() == getStat();
    }

    /**
     * Returns `true` if this is a virtual MeCab node representing the beginning of the sentence.
     * @return [boolean]
     */
    public boolean isBOS(){
        return NodeType.BOS_NODE.getId() == getStat();
    }

    /**
     * Returns `true` if this is a virtual MeCab node representing the end of the sentence.
     * @return [boolean]
     */
    public boolean isEOS(){
        return NodeType.EOS_NODE.getId() == getStat();
    }

    /**
     * Returns `true` if this is a virtual MeCab node representing the end of the node list.
     * @return [boolean]
     */
    public boolean isEON(){
        return NodeType.EON_NODE.getId() == getStat();
    }

    // FIXME : implements later.
    @Override
    public java.lang.String toString(){
        return super.toString();
    }
}
