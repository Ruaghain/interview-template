import React, { useEffect, useState } from 'react';
import { getAllUsers } from "./Users.model";
import UserList from "./UserList/UserList";

function Users() {
    const [data, setData] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const result = await getAllUsers();
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
