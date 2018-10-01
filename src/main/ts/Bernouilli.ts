export module duc {
    export module probability {
        export class Bernoulli {
            private _value: boolean;
            private _confidence: number;


            constructor(value: boolean, confidence: number) {
                this._value = value;
                this._confidence = confidence;
            }


            get value(): boolean {
                return this._value;
            }

            set value(value: boolean) {
                this._value = value;
            }

            get confidence(): number {
                return this._confidence;
            }

            set confidence(value: number) {
                this._confidence = value;
            }

            static or(b1: Bernoulli, b2: Bernoulli): Bernoulli {
                let confB1 = (b1.value) ? b1.confidence : 1 - b1.confidence;
                let confB2 = (b2.value) ? b2.confidence : 1 - b2.confidence;

                let value = b1.value || b2.value;
                let confidence = (value) ? (confB1 + confB2 - confB1 * confB2) : 1 - (confB1 + confB2 - confB1 * confB2);

                return new Bernoulli(value, confidence);
            }

            static and(b1: Bernoulli, b2: Bernoulli): Bernoulli {
                let confB1 = (b1.value) ? b1.confidence : 1 - b1.confidence;
                let confB2 = (b2.value) ? b2.confidence : 1 - b2.confidence;

                let value = b1.value && b2.value;
                let confidence = (value) ? confB1 * confB2 : 1 - (confB1 * confB2);


                return new Bernoulli(value, confidence);
            }

            static equals(b1: Bernoulli, b2: Bernoulli): boolean {
                let trueConfB1 = (b1.value) ? b1.confidence : 1 - b1.confidence;
                let trueConfB2 = (b2.value) ? b2.confidence : 1 - b2.confidence;

                return trueConfB1 == trueConfB2;
            }

            static notEquals(b1: Bernoulli, b2: Bernoulli): boolean {
                return !Bernoulli.equals(b1, b2);
            }

            static not(b1: Bernoulli): Bernoulli {
                return new Bernoulli(!b1.value, b1.confidence);
            }

            static resolveValue(b1: Bernoulli, conf:  number): boolean {
                let isValPossible = b1.confidence >= conf;
                let isOppositePossible = (1 - b1.confidence) >= conf;

                if(isValPossible && !isOppositePossible) {
                    return b1.value;
                }

                if(!isValPossible && isOppositePossible) {
                    return !b1.value;
                }

                if(!isValPossible && !isOppositePossible) {
                    return false;
                }

                if(b1.confidence >= (1-b1.confidence)) {
                    return b1.value;
                }

                return !b1.value;
            }

            static exists(b1: Bernoulli, conf: number): boolean {
                return b1.confidence >= conf || (1 -b1.confidence) >= conf;
            }
        }
    }
}