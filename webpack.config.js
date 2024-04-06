var path = require('path');

module.exports = {
    entry: './src/main/jsdev/main.jsx',
    devtool: 'source-map',
    cache: false,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/webapp/WEB-INF/js/bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.jsx$/ /* path.join(__dirname, '.') */,
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", ["@babel/preset-react", {"runtime": "automatic"}]]
                    }
                }]
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    }
};