import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/account';

export const AccountsModel = {
    get() {
        return axios.get(BASE_URL, {});
    }
};
