module.exports = function(mongoose){
    return [{
        artist: {type: String, required: true},
        song: {type: String},
        album: {type: String},
        year: {type: String},
    }, {
        timestamps: true,
        strict: false
    }]
};
