import axios from 'axios';
import {authToken} from './authToken';

export const getUser = () => {
  return axios
    .get(
      'http://localhost:10180/user/81718C54-4B2C-4131-AD0F-D8726B0A9F4B/demoUser',
      {
        headers: {
          Authorization: authToken,
        },
      },
    )
    .then((success) => {
      return success.data;
    })
    .catch((error) => {
      console.error(error);
    });
};
