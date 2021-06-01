/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */
import 'react-native-gesture-handler';
import React, {useState, useEffect} from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import {DefaultTheme, Provider as PaperProvider} from 'react-native-paper';
import Navbar from './components/navbar';
import HomeScreen from './screens/Homescreen';
import BudgetScreen from './screens/BudgetScreen';
import TransactionsScreen from './screens/TransactionsScreen';
import AddBudgetScreen from './screens/AddBudgetScreen';
import SettingsScreen from './screens/SettingsScreen';
import FraudAlertScreen from './screens/FraudAlertScreen';
import {getUser} from './services/userService';

const Stack = createStackNavigator();
const theme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: '#00A7E1',
    accent: '#ffffff',
  },
};

const App = () => {
  const [user, setUser] = useState({});
  useEffect(() => {
    getUser()
      .then((data) => {
        setUser(data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [user]);
  return (
    <PaperProvider theme={theme}>
      <NavigationContainer>
        <Stack.Navigator
          initialRouteName="home"
          screenOptions={{
            header: (props) => <Navbar {...props} />,
          }}>
          <Stack.Screen
            name="home"
            component={HomeScreen}
            options={{
              title: 'Hello, ' + user.firstName + ' ' + user.lastName,
            }}
          />
          <Stack.Screen
            name="budget"
            component={BudgetScreen}
            options={({route}) => ({
              title: route.params.accountName + "'s Budget",
            })}
          />
          <Stack.Screen
            name="addbudget"
            component={AddBudgetScreen}
            options={{title: 'Add Budget'}}
          />
          <Stack.Screen
            name="transactions"
            component={TransactionsScreen}
            options={({route}) => ({
              title: route.params.accountName + "'s Activity",
            })}
          />
          <Stack.Screen
            name="settings"
            component={SettingsScreen}
            options={{title: 'Settings'}}
            initialParams={{
              user: user,
            }}
          />
          <Stack.Screen
            name="fraudalert"
            component={FraudAlertScreen}
            options={{title: 'Potential Fraud'}}
          />
        </Stack.Navigator>
      </NavigationContainer>
    </PaperProvider>
  );
};

export default App;
