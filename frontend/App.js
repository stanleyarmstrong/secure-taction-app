/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */
import 'react-native-gesture-handler';
import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import Navbar from './components/navbar';
import HomeScreen from './screens/Homescreen';
import BudgetScreen from './screens/BudgetScreen';
import Settings from './screens/Settings';

const Stack = createStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName="home"
        screenOptions={{
          header: (props) => <Navbar {...props} />,
        }}>
        <Stack.Screen
          name="home"
          component={HomeScreen}
          options={{title: 'Good Morning, First Last'}}
        />
        <Stack.Screen
          name="budget"
          component={BudgetScreen}
          options={{title: "Card's Budget"}}
        />
        <Stack.Screen
          name="settings"
          component={Settings}
          options={{title: 'Settings'}}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
