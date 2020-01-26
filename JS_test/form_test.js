import {formDocument} from './form.js';

describe("positive scenario ", function(){
    it("css change", function(){
        let number = {value: 12 , id:"number"};
        let twoCharText= {value:"PL", id:"twoChartText"};
        formDocument(number, twoCharText);
        expect(document.getElementById(number.id).className).toBe("correct");
        expect(document.getElementById(twoCharText.id).className).toBe("correct");
    });
})

describe("All wrong", function(){
    it("css change", function(){
        let number = {value: "string" , id:"number"};
        let twoCharText= {value:"long_long_string", id:"twoChartText"};

        formDocument(number, twoCharText);
        expect(document.getElementById(number.id).className).toBe("incorrect");
        expect(document.getElementById(twoCharText.id).className).toBe("incorrect");
    });
})

describe("You can use only first element", function(){
    it("css change", function(){
        let number = {value: 123456 , id:"number"};
        let twoCharText= {value:"long_long_string", id:"twoChartText"};
        formDocument(number, twoCharText);
        expect(document.getElementById(number.id).className).toBe("correct");
    });
})

describe("You can use only first element", function(){
    it("css change", function(){
        let number = {value: 123456 , id: "number"};
        let twoCharText= {value: 12345678909876543, id: "twoChartText"};

        formDocument(number,twoCharText);
        expect(document.getElementById(number.id).className).toBe("correct");
        expect(document.getElementById(twoCharText.id).className).toBe("correct");
    });
})
