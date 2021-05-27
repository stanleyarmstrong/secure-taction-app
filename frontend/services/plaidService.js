import axios from 'axios';
import base64 from 'react-native-base64';

const authToken = 'Basic ' + base64.encode('asdfjkl:asdfjkl');
export const getLinkToken = () => {
  return axios
    .get(
      'http://localhost:10180/plaid/create_link_token/81718C54-4B2C-4131-AD0F-D8726B0A9F4B/demoUser',
      {
        headers: {
          Authorization: authToken,
        },
      },
    )
    .then((success) => {
      return success.data.linkToken;
    })
    .catch((error) => {
      return error;
    });
};

export const tokenExchange = (public_token, userId, username) => {
  return axios
    .post(
      'http://localhost:10180/plaid/get_access_token',
      {
        publicToken: public_token,
        userId: userId,
        userName: username,
      },
      {
        headers: {
          Authorization: authToken,
        },
      },
    )
    .then((success) => {
      return success;
    })
    .catch((error) => {
      console.error('Request failed:' + error);
    });
};
