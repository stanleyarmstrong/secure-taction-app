import axios from 'axios';
import base64 from 'react-native-base64';

const authToken = 'Basic ' + base64.encode('asdfjkl:asdfjkl');
export const getAccounts = () => {
  return axios
    .get('http://localhost:10180/user/81718C54-4B2C-4131-AD0F-D8726B0A9F4B', {
      headers: {
        Authorization: authToken,
      },
    })
    .then((success) => {
      return success.data;
    })
    .catch((error) => {
      console.error(error);
    });
};

export const deleteAccount = (accountId) => {
  return axios
    .delete(
      'http://localhost:10180/account/' + 
        accountId + '/81718C54-4B2C-4131-AD0F-D8726B0A9F4B', {
      headers: {
        Authorization: authToken,
      },
    })
    .then((success) => {
      return success.status;
    })
    .catch((error) => {
      console.error(error);
      return error;
    });
};
