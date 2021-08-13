import React from "react";
import { DataGrid } from '@material-ui/data-grid';
import * as PropTypes from "prop-types";
import { Button } from "@material-ui/core";

const columns = [
    {field: 'id', headerName: 'ID', width: 90},
    {
        field: 'userName',
        headerName: 'Username',
        width: 150,
        editable: false
    },
    {
        field: 'firstName',
        headerName: 'First Name',
        width: 150,
        editable: false
    },
    {
        field: 'lastName',
        headerName: 'Last Name',
        width: 150,
        editable: false
    },
    {
        field: 'email',
        headerName: 'Email Address',
        width: 300,
        editable: false
    },
    {
        field: 'edit',
        headerName: 'Edit',
        width: 300,
        editable: false,
        renderCell: (params) => (
            <div>
                {(params.value)}
                <Button
                    variant="contained"
                    color="primary"
                    size="small"
                    style={{marginLeft: 16}}
                >
                    Edit
                </Button>
            </div>
        )
    }
];

function UserList(props) {
    return (
        <div style={{height: 400, width: '100%'}}>
            <DataGrid
                rowsPerPageOptions={[5, 10, 25, 50, 100]}
                rows={props.userData}
                columns={columns}
                pageSize={5}
            />
        </div>
    );
}

UserList.propTypes = {
    userData: PropTypes.array
};

export default UserList
