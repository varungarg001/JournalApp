package net.engineeringdigest.journalApp.constants;

public enum PlaceHolders {

        CITY("<CITY>"),
        API_KEY("<API_KEY>");

        private final String value;

        PlaceHolders(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

