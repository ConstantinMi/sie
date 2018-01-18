package ro.uaic.feaa.common.enums;

/**
 * Created by Claudiu on 1/13/2018.
 */
public enum TipCont {
    STOCURI {
        @Override
        public String toString() {
            return "Stocuri";
        }
    },
    CREANTE_TOTALE {
        @Override
        public String toString() {
            return "Creante totale";
        }
    },
    DATORII_RESURSE_CURENTE {
        @Override
        public String toString() {
            return "Datorii si resurse curente";
        }
    },
    CAPITAL_PROPRIU {
        @Override
        public String toString() {
            return "Capital propriu";
        }
    },
    ACTIVE_IMOBILIZATE {
        @Override
        public String toString() {
            return "Active imobilizate";
        }
    },
    DATORII_TERMEN_LUNG {
        @Override
        public String toString() {
            return "Datorii pe termen lung";
        }
    },
    VENITURI {
        @Override
        public String toString() {
            return "Venituri";
        }
    }
}
