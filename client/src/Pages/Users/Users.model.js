import axios from 'axios';
import { setupUsersMock } from "./Users.mock";

const BASE_URL = 'http://localhost:8080/api/v1/user';

const useMocks = process.env.REACT_APP_USE_MOCKS;

const client = axios.create({
        baseURL: BASE_URL
    }
);

if (useMocks) {
    setupUsersMock(client);
}

export const getAllUsers = () => {
    return client.get(BASE_URL);
}

export const getUser = (id) => {
    return client.get(`/${id}`)
}

