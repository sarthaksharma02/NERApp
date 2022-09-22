import json
import spacy
from flask import Flask
import re

app = Flask(__name__)
app.config['DEBUG'] = True

global data

@app.route('/name', methods = ['GET'])
def fun():
    text = "My name is Sarthak Sharma and I am currently situated in New Delhi, and my phone number is (403) 431 8716 and email is probably stksharma@gmail.com. I am glad to hear that Ajay was also annoyed due to few matters."
    d = {}
    l = []
    a = []
    b = []
    nlp = spacy.load('en_core_web_sm')
    data = nlp(text)
    x = re.findall('[a-zA-Z0-9_.+-]+@[a-zA-Z]+[.][a-zA-Z]+', text)
    y = re.findall('[(]?\d+[)] ]?[0-9]+[ ]?[0-9]+', text)
    d['Email'] = x[0]
    d['Phone'] = y[0]
    for ents in data.ents:
        if ents.label_ == "PERSON":
            l.append(ents.text)
        if ents.label_ == "GPE":
            a.append(ents.text)
        if ents.label_ == "SKILLS":
            b.append(ents.text)
    d["Name"] = l
    d["GPE"] = a
    d["SKILLS"] = b
    return json.dumps(d)

if __name__ == '__main__':
    app.run(host='192.168.1.6',port=4000)