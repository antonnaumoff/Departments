const webpack = require("webpack");
const path = require('path');

module.exports = {
    entry: {
        app: ["./src/main.js"]
    },
    resolve: {
        modulesDirectories: [
            "node_modules"
        ],
        alias: {
            '/src': './src'
        },
        extensions: ['', '.json', '.js']
    },
    resolveLoader: {
        modulesDirectories: ['node_modules']
    },
    output: {
        path: path.resolve(__dirname, "build"),
        filename: "bundle.js"
    },
    devServer: {
        contentBase: './build'
    }
}