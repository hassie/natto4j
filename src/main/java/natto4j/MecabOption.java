package natto4j;

import org.kohsuke.args4j.Option;

/**
 * for parsing the various MeCab options supported by `Natto4j`.
 */
public class MecabOption {
    @Option(name = "-r", aliases = "--rcfile", metaVar = "<FILE>", usage = "use FILE as resource file")
    private String rcFile;

    @Option(name = "-d", aliases = "--dicdir", metaVar = "<DIR>", usage = "set DIR  as a system dicdir")
    private String dicDir;

    @Option(name = "-u", aliases = "--userdic", metaVar = "<FILE>", usage = "use FILE as a user dictionary")
    private String userDic;

    @Option(name = "-l", aliases = "--lattice-level", metaVar = "<INT>", usage = "lattice information level (DEPRECATED)")
    private Integer latticeLevel; //!deprecated in 0.99!!!

    @Option(name = "-O", aliases = "--output-format-type", metaVar = "<TYPE>", usage = "set output format type (wakati,none,...)")
    private String outputForatType;

    @Option(name = "-a", aliases = "--all-morphs", usage = "output all morphs(default false)")
    private String allMorphs;

    @Option(name = "-N", aliases = "--nbest", metaVar = "<INT>", usage = "output N best results (default 1)")
    private Integer nBest;

    @Option(name = "-p", aliases = "--partial", usage = "partial parsing mode (default false)")
    private Boolean partial;

    @Option(name = "-m", aliases = "--marginal", usage = "output marginal probability (default false)")
    private Boolean marginal;

    @Option(name = "-M", aliases = "--max-grouping-size", metaVar = "<INT>", usage = "maximum grouping size for unknown words (default 24)")
    private Integer maxGroupingSize;

    @Option(name = "-F", aliases = "--node-format", metaVar = "<STR>", usage = "use STR as the user-defined node format")
    private String nodeFormat;

    @Option(name = "-U", aliases = "--unk-format", metaVar = "<STR>", usage = "use STR as the user-defined unknown node format")
    private String unkFormat;

    @Option(name = "-B", aliases = "--bos-format", metaVar = "<STR>", usage = "use STR as the user-defined beginning-of-sentence format")
    private String bosFormat;

    @Option(name = "-E", aliases = "--eos-format", metaVar = "<STR>", usage = "use STR as the user-defined end-of-sentence format")
    private String eosFormat;

    @Option(name = "-S", aliases = "--eon-format", metaVar = "<STR>", usage = "use STR as the user-defined end-of-NBest format")
    private String eonFormat;

    @Option(name = "-x", aliases = "--unk-feature", metaVar = "<STR>", usage = "use STR as the feature for unknown word")
    private String unkFeature;

    @Option(name = "-b", aliases = "--input-buffer-size", metaVar = "<INT>", usage = "set input buffer size (default 8192)")
    private Integer inputBufferSize;

    @Option(name = "-C", aliases = "--allocate-sentence", usage = "allocate new memory for input sentence")
    private Boolean allocateSentence;

    @Option(name = "-t", aliases = "--theta", metaVar = "<FLOAT>", usage = "set temparature parameter theta (default 0.75)")
    private Float theta;

    @Option(name = "-c", aliases = "--cost-factor", metaVar = "<INT>", usage = "set cost factor (default 700)")
    private Integer costFactor;

    public String getRcFile() {
        return rcFile;
    }

    public void setRcFile(String rcFile) {
        this.rcFile = rcFile;
    }

    public String getDicDir() {
        return dicDir;
    }

    public void setDicDir(String dicDir) {
        this.dicDir = dicDir;
    }

    public String getUserDic() {
        return userDic;
    }

    public void setUserDic(String userDic) {
        this.userDic = userDic;
    }

    public Integer getLatticeLevel() {
        return latticeLevel;
    }

    public void setLatticeLevel(Integer latticeLevel) {
        this.latticeLevel = latticeLevel;
    }

    public String getOutputForatType() {
        return outputForatType;
    }

    public void setOutputForatType(String outputForatType) {
        this.outputForatType = outputForatType;
    }

    public String getAllMorphs() {
        return allMorphs;
    }

    public void setAllMorphs(String allMorphs) {
        this.allMorphs = allMorphs;
    }

    public Integer getnBest() {
        return nBest;
    }

    public void setnBest(Integer nBest) {
        this.nBest = nBest;
    }

    public Boolean getPartial() {
        return partial;
    }

    public void setPartial(Boolean partial) {
        this.partial = partial;
    }

    public Boolean getMarginal() {
        return marginal;
    }

    public void setMarginal(Boolean marginal) {
        this.marginal = marginal;
    }

    public Integer getMaxGroupingSize() {
        return maxGroupingSize;
    }

    public void setMaxGroupingSize(Integer maxGroupingSize) {
        this.maxGroupingSize = maxGroupingSize;
    }

    public String getNodeFormat() {
        return nodeFormat;
    }

    public void setNodeFormat(String nodeFormat) {
        this.nodeFormat = nodeFormat;
    }

    public String getUnkFormat() {
        return unkFormat;
    }

    public void setUnkFormat(String unkFormat) {
        this.unkFormat = unkFormat;
    }

    public String getBosFormat() {
        return bosFormat;
    }

    public void setBosFormat(String bosFormat) {
        this.bosFormat = bosFormat;
    }

    public String getEosFormat() {
        return eosFormat;
    }

    public void setEosFormat(String eosFormat) {
        this.eosFormat = eosFormat;
    }

    public String getEonFormat() {
        return eonFormat;
    }

    public void setEonFormat(String eonFormat) {
        this.eonFormat = eonFormat;
    }

    public String getUnkFeature() {
        return unkFeature;
    }

    public void setUnkFeature(String unkFeature) {
        this.unkFeature = unkFeature;
    }

    public Integer getInputBufferSize() {
        return inputBufferSize;
    }

    public void setInputBufferSize(Integer inputBufferSize) {
        this.inputBufferSize = inputBufferSize;
    }

    public Boolean getAllocateSentence() {
        return allocateSentence;
    }

    public void setAllocateSentence(Boolean allocateSentence) {
        this.allocateSentence = allocateSentence;
    }

    public Float getTheta() {
        return theta;
    }

    public void setTheta(Float theta) {
        this.theta = theta;
    }

    public Integer getCostFactor() {
        return costFactor;
    }

    public void setCostFactor(Integer costFactor) {
        this.costFactor = costFactor;
    }


}
