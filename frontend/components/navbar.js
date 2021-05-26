import React, {useState} from 'react';
import {StyleSheet} from 'react-native';
import {Appbar, Text, Menu} from 'react-native-paper';
import {View} from 'react-native';

const Navbar = ({navigation, previous, scene}) => {
  const [visible, setVisible] = useState(false);
  const {options} = scene.descriptor;
  const leftIcon = previous ? 'chevron-left' : 'account-circle-outline';
  const rightIcon = previous ? 'home-outline' : 'bell';
  const title = options.title ? options.title : 'Secure Taction';
  // replace with state after figuring out notifications
  const notifications = [
    {
      id: 0,
      vendor: 'Something Sketchy',
      amount: 600,
      account: 'Chase Checking',
      visited: false,
    },
    {
      id: 1,
      vendor: 'Not Sketchy',
      amount: 5.42,
      account: 'Chase Checking',
      visited: false,
    },
  ]
    .filter((notif) => !notif.visited)
    .map((notif) => {
      return (
        <Menu.Item
          key={notif.id}
          title={notif.vendor + ' Purchase Alert'}
          onPress={() => {
            setVisible(false);
            navigation.push('fraudalert', {
              vendor: notif.vendor,
              amount: notif.amount,
              account: notif.account,
            });
          }}
        />
      );
    });
  return (
    <View>
      <Appbar.Header style={styles.barColor}>
        <Appbar.Action
          icon={leftIcon}
          size={35}
          onPress={() => {
            return leftIcon === 'chevron-left'
              ? navigation.pop()
              : navigation.push('settings');
          }}
        />
        <Appbar.Content title={title} titleStyle={styles.title} />
        <Menu
          visible={visible}
          onDismiss={() => setVisible(false)}
          anchor={
            <Appbar.Action
              icon={rightIcon}
              size={35}
              color="#FFFFFF"
              onPress={() => {
                return rightIcon === 'bell'
                  ? setVisible(true)
                  : navigation.popToTop();
              }}
            />
          }>
          {notifications}
        </Menu>
      </Appbar.Header>
    </View>
  );
};

const styles = StyleSheet.create({
  title: {
    fontSize: 18,
  },
  icon: {
    backgroundColor: '#ffffff',
  },
});

export default Navbar;
