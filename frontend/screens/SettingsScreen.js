import React, {useState} from 'react';
import {View, StyleSheet} from 'react-native';
import {Card, Button, TextInput} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';

const SettingsScreen = () => {
  const navigation = useNavigation();
  // replace with state
  const user = {
    userId: 0,
    userName: 'stanleycodes',
    email: 'stanley@sharklasers.com',
    password: 'somethiing',
    firstName: 'Stanley',
    lastName: 'Armstrong',
  };
  const [clicked, setClicked] = useState(false);
  const [email, setEmail] = useState('');
  const [firstName, setFirstName] = useState('');
  const [userName, setUserName] = useState('');
  const [lastName, setLastName] = useState('');
  const account = (
    <View>
      <Button icon="cog" onPress={() => setClicked(true)}>
        Edit Account Info
      </Button>
      <Button
        mode="outlined"
        color="#FF4438"
        onPress={() => {
          navigation.popToTop();
        }}>
        Sign Out
      </Button>
    </View>
  );
  const form = (
    <View>
      <TextInput
        mode="outlined"
        value={userName}
        label="Username"
        placeholder="ex: stanleycodes"
        onChangeText={(name) => setUserName(name)}
        theme={theme}
        style={styles.input}
      />
      <TextInput
        mode="outlined"
        value={email}
        label="Email"
        placeholder="ex: stanleycodes@twitch.tv"
        onChangeText={(em) => setEmail(em)}
        theme={theme}
        style={styles.input}
      />
      <TextInput
        mode="outlined"
        value={firstName}
        label="First Name"
        placeholder="ex: Stanley"
        onChangeText={(first) => setFirstName(first)}
        theme={theme}
        style={styles.input}
      />
      <TextInput
        mode="outlined"
        value={lastName}
        label="Last Name"
        placeholder="ex: Armstrong"
        onChangeText={(last) => setLastName(last)}
        theme={theme}
        style={styles.input}
      />
      <Button
        mode="contained"
        color="#389635"
        dark
        onPress={() => {
          //need to add user id and account id to object
          const obj = {
            userName: userName,
            email: email,
            password: user.password,
            firstName: firstName,
            lastName: lastName,
          };
          // service call instead of console.log
          console.log(obj);
          setClicked(false);
        }}>
        Done
      </Button>
    </View>
  );
  return (
    <View style={styles.shell}>
      <Card style={styles.inner}>
        <Card.Title
          title={'Hello, ' + user.firstName + ' ' + user.lastName}
          style={styles.title}
          titleStyle={styles.titleColor}
        />
        <Card.Content>
          {clicked ? form : account} 
        </Card.Content>
      </Card>
    </View>
  );
};

const styles = StyleSheet.create({
  shell: {
    backgroundColor: '#E9E9E9',
    height: '100%',
  },
  inner: {
    shadowColor: '#000000',
    shadowOpacity: 20,
    borderRadius: 12,
    marginTop: 50,
    marginLeft: 25,
    marginRight: 25,
  },
  title: {
    color: '#FFFFFF',
    backgroundColor: '#00A7E1',
    fontSize: 24,
    borderRadius: 12,
  },
  titleColor: {
    color: '#FFFFFF',
  },
  newCard: {
    paddingTop: 10,
    textAlign: 'center',
  },
  input: {
    marginTop: 5,
    marginBottom: 10,
  },
});

const theme = {
  colors: {
    primary: '#00A7E1',
    background: '#FFFFFF',
  },
};
export default SettingsScreen;
