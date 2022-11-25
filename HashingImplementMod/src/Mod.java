public static int hashCode() {
    int value;
    if (this < -500) {
        value = 0;
    }
    else if (this < -100) {
        value = 1;
    }
    else if (this < -5) {
        value = 2;
    }
    else if (this < 0) {
        value = 3;
    }
    else if (this = 0) {
        value = 4;
    }
    else if (this < 50) {
        value = 5;
    }
    else if (this < 100) {
        value = 6;
    }
    else if (this < 500) {
        value = 7;
    }
    else if (this < 1000) {
        value = 8;
    }
    else {
        value = 9;
    }

    return value;
}
