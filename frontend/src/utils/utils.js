export const validateHandler = (param) => {
    if(param !== null && param !== '' && param !== undefined) {
        return true;
    } else {
        return false;
    }
}