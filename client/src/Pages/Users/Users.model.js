import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/v1/user';

export const UsersModel = {
    get() {
        return axios.get(BASE_URL, {});
    }
};
