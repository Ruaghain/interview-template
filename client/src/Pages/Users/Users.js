import React, { useEffect, useState } from 'react';
import { UsersModel } from "./Users.model";
import UserList from "./UserList/UserList";

function Users() {
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const result = await UsersModel.get();
            setData(result.data);
        }
        fetchData();
    }, []);

    return (
        <div>
            <UserList userData={data}/>
        </div>
    );
}

export default Users;
