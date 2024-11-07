from flask import Flask, send_from_directory
import os
app = Flask(__name__)

LOG4J_FOLDER = os.path.expanduser("~/log4j")

@app.route('/log4j/<path:filename>')
def serve_file(filename):
    return send_from_directory(LOG4J_FOLDER, filename)

@app.route('/')
def hello():
    return "Hello, World!"

if __name__=='__main__':
    app.run(host='0.0.0.0', port=8000)